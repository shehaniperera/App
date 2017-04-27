
int ledGreenLight = 11;
int ledRedLight = 12;
int smokeAnalogPin = A5;
int alarmBuzzer = 10;

// the thresh hold value
int sensorThresholdVal = 400; //400

void setup() {
  pinMode(ledRedLight, OUTPUT);
  pinMode(ledGreenLight, OUTPUT);
  pinMode(alarmBuzzer, OUTPUT);
  pinMode(smokeAnalogPin, INPUT);
  Serial.begin(9600); // setes the serial port value to 9600
}

void loop() {
  int analogSensorData = analogRead(smokeAnalogPin); // read the analog input pin no A5

 
  Serial.println(analogSensor); // pronts out the sensor read value

  // checks if the analog sesor data has reached the thresh hold value
  if (analogSensorData  > sensorThresholdVal)
  {
    digitalWrite(ledRedLight, HIGH);
    digitalWrite(ledGreenLight, LOW);
    tone(alarmBuzzer, 1000, 200);
    
  }
  else
  {
    digitalWrite(ledRedLight, LOW);
    digitalWrite(ledGreenLight, HIGH);
    noTone(alarmBuzzer);
  }
  delay(100); // waits for 100 milliseconds to read the next input
}
