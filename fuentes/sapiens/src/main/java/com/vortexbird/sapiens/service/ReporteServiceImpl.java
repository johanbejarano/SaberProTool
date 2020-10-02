package com.vortexbird.sapiens.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vortexbird.sapiens.utility.GlobalProperties;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Scope("singleton")
@Service
public class ReporteServiceImpl implements ReporteService {

	private static final Logger log = LoggerFactory.getLogger(ReporteServiceImpl.class);

	@Autowired
	private GlobalProperties globalProperties;

	@Autowired
	protected DataSource dataSource;

	@Override
	public String reportePruebaEstudiante(Integer prueId, Integer estuId) throws Exception {
		String rutaReporte = "/ReportePruebaEstudiante.jasper";
		// Crea la variable de parametros
		Map<String, Object> params = new HashMap<String, Object>();

		// Asigna los parametros enviados
		params.put("P_PRUE_ID", new Long(prueId));
		params.put("P_USUA_ID", new Long(estuId));

		String reporte = generarReporte(rutaReporte, params);

		return reporte;

	}

	@Override
	public String reportePruebaModulo(Long prueId) throws Exception {
		String rutaReporte = "/ReportePruebaEstudiante.jasper";
		// Crea la variable de parametros
		Map<String, Object> params = new HashMap<String, Object>();

		// Asigna los parametros enviados
		params.put("P_PRUE_ID", new Long(prueId));

		String reporte = generarReporte(rutaReporte, params);

		return reporte;

	}

	@Override
	public String reportePruebaPrograma(Long prueId) throws Exception {
		String rutaReporte = "/ReportePruebaEstudiante.jasper";
		// Crea la variable de parametros
		Map<String, Object> params = new HashMap<String, Object>();

		// Asigna los parametros enviados
		params.put("P_PRUE_ID", new Long(prueId));

		String reporte = generarReporte(rutaReporte, params);

		return reporte;

	}

	@Override
	public String reportePruebaDetalleEstudiante(Long prueId) throws Exception {
		String rutaReporte = "/ReportePruebaEstudiante.jasper";
		// Crea la variable de parametros
		Map<String, Object> params = new HashMap<String, Object>();

		// Asigna los parametros enviados
		params.put("P_PRUE_ID", new Long(prueId));

		String reporte = generarReporte(rutaReporte, params);

		return reporte;

	}

	private String generarReporte(String rutaReporte, Map<String, Object> params) throws Exception {
		ByteArrayInputStream informe = null;
		InputStream inputStream = null;
		ByteArrayOutputStream bos = null;
		Connection connection = null;
		try {
			// Se valida si la ruta existe
			File fRutaBaseReportes = new File(globalProperties.getSUBREPORT_DIR());

			if (!fRutaBaseReportes.exists() || !fRutaBaseReportes.isDirectory() || !fRutaBaseReportes.canRead()) {
				throw new Exception(
						"No existe la ruta base de reportes, no es un directorio o no se tiene acceso de lectura al directorio: "
								+ fRutaBaseReportes.getPath());
			}

			// Se valida la ruta del reporte
			File fReporte = new File(fRutaBaseReportes, rutaReporte);
			if (!fReporte.exists() || !fReporte.isFile() || !fReporte.canRead()) {
				throw new Exception(
						"No existe la ruta del reporte, no es un archivo o no se tiene acceso de lectura al mismo: "
								+ fReporte.getPath());
			}

			// Se abre el reporte
			inputStream = new FileInputStream(fReporte);

			// Se envía la ruta de parámetos siempre
			params.put("pSubreportDir", (fRutaBaseReportes.getPath().endsWith("/") ? fRutaBaseReportes.getPath()
					: (fRutaBaseReportes.getPath() + "/")));

			// Crea la conexión a la base de datos
			connection = dataSource.getConnection();

			bos = new ByteArrayOutputStream();
			// Se rellena el reporte
			JasperPrint print = JasperFillManager.fillReport(inputStream, params, connection);

			// Se exporta el reporte a pdf
			JRPdfExporter jrPdfExporter = new JRPdfExporter();

			jrPdfExporter.setExporterInput(new SimpleExporterInput(print));
			jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(bos));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			jrPdfExporter.setConfiguration(configuration);

			jrPdfExporter.exportReport();

			byte[] bytes = bos.toByteArray();

			String base64 = DatatypeConverter.printBase64Binary(bytes);
			return base64;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (informe != null) {
				informe.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (bos != null) {
				bos.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		}
	}
}
