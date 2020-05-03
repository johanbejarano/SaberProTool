package com.vortexbird.sapiens.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;


@RestController
@RequestMapping("/api/public")
public class UploadRestController {

	Logger log = LoggerFactory.getLogger(UploadRestController.class);
	
	@Value("${sapiens.image.base.path}")
	private String sapiensImageBasepath;
	
	@Value("${sapiens.image.api.url}")
	private String sapiensImageApiUrl;
	
	@PostMapping(value = "/upload", produces = "application/json")
	public ResponseEntity<String> upload(MultipartRequest multipartRequest){
	
		try {
			
			log.info("Llegó una petición de upload para guardar en " + sapiensImageBasepath);
			
			Map<String, MultipartFile> mapa = multipartRequest.getFileMap();
			Set<String> llaves = mapa.keySet();
			for (String llave : llaves) {
				
				MultipartFile multipartFile = mapa.get(llave);
				
				String originalFilename = multipartFile.getOriginalFilename();
				String filename = (new Date()).getTime() + "_" + originalFilename;
				byte[] bytes = multipartFile.getBytes();
				String contentType = multipartFile.getContentType();
				long size = multipartFile.getSize();
				
				log.info("\n\tId:" + llave + "\n" + 
						"\tNombre: " + originalFilename + "\n" + 
						"\tContentType: " + contentType + "\n" + 
						"\tSize: " + size
						);
				
				File nuevoArchivo = new File(sapiensImageBasepath, filename);
				FileOutputStream fos = new FileOutputStream(nuevoArchivo);
				fos.write(bytes);
				fos.close();
				
				log.info("Se ha almacenado el archivo " + nuevoArchivo.getPath());
				
				String response = "{" + 
	                	  "    \"uploaded\": true," + 
	                	  //"    \"url\": \"https://www.infobae.com/new-resizer/4nEEHHVs5-II9SXlh4b4DI34iU4=/750x0/filters:quality(100)/s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2018/05/01181948/05-Salma-Hayek.jpg\"" +
	                	  "		\"url\": \"" + sapiensImageApiUrl + filename + "\"" + 
	                	  "}";
				
				log.info(response);
				
				return ResponseEntity.ok()
		                .body(response);
				
			}
			
			return ResponseEntity.badRequest()
					.body("{" + 
							"    \"uploaded\": false," + 
							"    \"error\": {" + 
							"        \"message\": \"could not upload this image\"" + 
							"    }" + 
							"}");
			
			
		}catch (Exception e) {
			log.error("Error cargando la imagen", e);
			
			return ResponseEntity.badRequest()
					.body("{" + 
							"    \"uploaded\": false," + 
							"    \"error\": {" + 
							"        \"message\": \"could not upload this image\"" + 
							"    }" + 
							"}");
		}
		
		
	}
	
	@GetMapping("/image/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        
		
        // Try to determine file's content type
        String contentType = null;
        try {
        	
        	// Load file as Resource
    		Path path = Paths.get(sapiensImageBasepath, fileName);
    		Resource resource = new UrlResource(path.toUri());
        	
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            
            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
            
        } catch (Exception ex) {
            log.error("Could not determine file type.", ex);
            return ResponseEntity.badRequest()
					.body(null);
        }

        
    }
}
