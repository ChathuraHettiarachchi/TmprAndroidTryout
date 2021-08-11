package com.example.temper

import com.example.temper.helpers.converted
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.nio.charset.Charset
import java.util.*

class TemperApiAvailabilityTest {
    @Test
    @Throws(Exception::class)
    fun testAvailability() {
        val date = (Calendar.getInstance().time).converted()
        val connectionString = URL("https://temper.works/api/v3/shifts?filter%5Bdate%5D=$date").openConnection()
        val responseFromApi = connectionString.getInputStream()
        val bufferData = StringBuffer()
        BufferedReader(InputStreamReader(responseFromApi, Charset.defaultCharset())).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                bufferData.append(line)
            }
        }
        assert(bufferData.length > 0)
    }
}