package org.marouan.dev.infra.out;

import java.io.InputStream;

public class FileReader {

  public InputStream getFileAsIOStream(final String fileName)
  {
    final InputStream ioStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream(fileName);

    if (ioStream == null) {
      throw new IllegalArgumentException(fileName + " is not found");
    }
    return ioStream;
  }
}
