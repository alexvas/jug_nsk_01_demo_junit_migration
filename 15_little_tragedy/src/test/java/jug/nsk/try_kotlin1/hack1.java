package jug.nsk.try_kotlin1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

class TheCovetousKnight {

    @Test
    void stealResource() throws IOException {

        try(InputStreamReader isr = sampleIsr()) {
            BufferedReader br = new BufferedReader(isr);

            while(true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("main: " + line);
            }
        }

    }

    @NotNull
    private InputStreamReader sampleIsr() {
        return new InputStreamReader(TheCovetousKnight.class.getResourceAsStream("/sample.txt"));
    }


}