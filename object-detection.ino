int irSensor = 7;  
int ledPin = 13;  
void setup() {
  pinMode(irSensor, INPUT);  
  pinMode(ledPin, OUTPUT); 
}

void loop() {
  int sensorValue = digitalRead(irSensor); 

  if (sensorValue == LOW) { 
    digitalWrite(ledPin, HIGH);  
  } else {
    digitalWrite(ledPin, LOW);  
  }
}