/* 
 * Copyright (c) 2018 AT&T Intellectual Property. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.akraino.portal.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {

    /**
     * Write an array of bytes to a file, converting CRLF to LF along the way.
     * @param filePath the pathname of the file to write
     * @param fileContent the array of bytes to write
     * @throws IOException if an I/O problem occurs
     */
    public static void writeToFile(final String filePath, final byte[] fileContent) throws IOException {

        FileOutputStream out = null;
        try {
            // Convert CRLF to LF
            String s = new String(fileContent).replaceAll("\r\n", "\n");

            Path path = Paths.get(filePath);
            Files.createDirectories(path.getParent());

            File file = new File(filePath);
            out = new FileOutputStream(file);
            out.write(s.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}