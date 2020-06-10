package com.ijikod.sensyne

import android.app.Application
import android.content.Context
import android.os.Environment
import java.io.*

class FileHelper {


    companion object{


         fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
            inputStream.use { input ->
                val outputStream = FileOutputStream(outputFile)
                outputStream.use { output ->
                    val buffer = ByteArray(4 * 1024)
                    while (true) {
                        val byteCount = input.read(buffer)
                        if (byteCount < 0) break
                        output.write(buffer, 0, byteCount)
                    }
                    output.flush()
                }
            }
        }

         fun createFile(context: Context, fileName: String, fileExt: String): File? {
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.path
            var file = File("$storageDir/$fileName.$fileExt")
            return storageDir?.let { file }
        }

        fun readFile(app: Context): BufferedReader {
            val file = File(app.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Hospital.csv")
            return (if (file.exists()) {
                BufferedReader(FileReader(File(app.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Hospital.csv")))
            } else null) as BufferedReader
        }
    }
}