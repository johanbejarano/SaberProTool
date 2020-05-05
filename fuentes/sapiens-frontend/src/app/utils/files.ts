export function base64ToArrayBuffer(base64: string) {
    const binaryString = window.atob(base64); // Comment this if not using base64
    const bytes = new Uint8Array(binaryString.length);
    return bytes.map((byte, i) => binaryString.charCodeAt(i));
}

export function createAndDownloadBlobFile(body, filename, extension) {

  let linkSource;

  if(extension === 'pdf'){
    linkSource = 'data:application/pdf;base64,' + body;
  }

  if(extension === 'xlsx'){
    linkSource = 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,' + body;
  }
  
  const downloadLink = document.createElement("a");
  const fileName = `${filename}.${extension}`;

  downloadLink.href = linkSource;
  downloadLink.download = fileName;
  downloadLink.click();
}

