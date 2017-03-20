/*
 * Copyright 2017, Paul Trebilcox-Ruiz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*

    Initializing with a Raspberry Pi 3B and a TMP36 connected to ADC CH 0.
    Can change these pin numbers in your own projects to be the proper board pins.

    Pinout for sample like so:

    ADC channel 0 -|*   |- 5v in
    ADC channel 1 -|    |- 5v in
    ADC channel 2 -|    |- Analog GND (For this sample app, I used this ground)
    ADC channel 3 -|    |- Clock
    ADC channel 4 -|    |- MISO pin on board (sometimes listed as D-OUT on chip diagram)
    ADC channel 5 -|    |- MOSI pin on board (sometimes listed as DIN on chip diagram)
    ADC channel 6 -|    |- Chip select (CS)
    ADC channel 7 -|    |- Digital GND (For this sample app, I left this GND disconnected)

    I wired BCM12 to CS, BCM21 to Clock, BCM16 to MOSI (D-OUT) and BCM20 to MISO (D-IN)
 */

package com.paultrebilcoxruiz.mcp3008;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends Activity {

    private MCP3008 mMCP3008;
    private Handler mHandler;

    private Runnable mReadAdcRunnable = new Runnable() {

        private static final long DELAY_MS = 3000L; // 3 seconds

        @Override
        public void run() {
            if (mMCP3008 == null) {
                return;
            }

            try {
                Log.e("MCP3008", "ADC 0: " + mMCP3008.readAdc(0x0));
                Log.e("MCP3008", "ADC 1: " + mMCP3008.readAdc(0x1));
                Log.e("MCP3008", "ADC 2: " + mMCP3008.readAdc(0x2));
                Log.e("MCP3008", "ADC 3: " + mMCP3008.readAdc(0x3));
                Log.e("MCP3008", "ADC 4: " + mMCP3008.readAdc(0x4));
                Log.e("MCP3008", "ADC 5: " + mMCP3008.readAdc(0x5));
                Log.e("MCP3008", "ADC 6: " + mMCP3008.readAdc(0x6));
                Log.e("MCP3008", "ADC 7: " + mMCP3008.readAdc(0x7));
            } catch( IOException e ) {
                Log.e("MCP3008", "Something went wrong while reading from the ADC: " + e.getMessage());
            }

            mHandler.postDelayed(this, DELAY_MS);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mMCP3008 = new MCP3008("BCM12", "BCM21", "BCM16", "BCM20");
            mMCP3008.register();
        } catch( IOException e ) {
            Log.e("MCP3008", "MCP initialization exception occurred: " + e.getMessage());
        }

        mHandler = new Handler();
        mHandler.post(mReadAdcRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if( mMCP3008 != null ) {
            mMCP3008.unregister();
        }

        if( mHandler != null ) {
            mHandler.removeCallbacks(mReadAdcRunnable);
        }
    }
}
