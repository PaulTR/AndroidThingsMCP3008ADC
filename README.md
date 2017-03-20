Paul Trebilcox-Ruiz
PaulTR@Gmail.com

Library for communicating with the MCP3008 Analog to Digital Converter.

Based on an Arduino library by: Uros Petrevski (https://github.com/nodesign/MCP3008)

Originally ported from Python code originaly written by Adafruit learning system for rPI:
    http://learn.adafruit.com/send-raspberry-pi-data-to-cosm/python-script

Initializing with a Raspberry Pi 3B and a TMP36 connected to ADC CH 0.

Pinout for sample like so:

     ADC channel 0 -|*   |- VIN
     ADC channel 1 -|    |- VIN
     ADC channel 2 -|    |- Analog GND (For this sample app, I used this ground)
     ADC channel 3 -|    |- Clock
     ADC channel 4 -|    |- MISO pin on board (sometimes listed as D-OUT on chip diagram)
     ADC channel 5 -|    |- MOSI pin on board (sometimes listed as DIN on chip diagram)
     ADC channel 6 -|    |- Chip select (CS)
     ADC channel 7 -|    |- Digital GND (For this sample app, I left this GND disconnected)

While testing I wired BCM12 to CS, BCM21 to Clock, BCM16 to MOSI (D-OUT) and BCM20 to MISO (D-IN)

Can change pin numbers in your own projects to be the proper board pins.