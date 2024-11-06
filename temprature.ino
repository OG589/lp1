#include <DHT.h>
#define DHTPIN 2
#define DHTTYPE DHT11 

#define LED_PIN 13 
    DHT dht(DHTPIN, DHTTYPE);
void setup()
{
    Serial.begin(9600);
    dht.begin();

    pinMode(LED_PIN, OUTPUT); 
}
void loop()
{
    delay(2000); 
    
    float humidity = dht.readHumidity();
    float temperature = dht.readTemperature();
  
    if (isnan(humidity) || isnan(temperature))
    {
        Serial.println("Failed to read from DHT sensor!");
        return;
    }
   
    Serial.print("Humidity: ");
    Serial.print(humidity);
    Serial.print(" %\t");
    Serial.print("Temperature: ");
    Serial.print(temperature);
    Serial.println(" °C");
  
    if (temperature > 26.0)
    {
        digitalWrite(LED_PIN, HIGH); 
    }
    else
    {
        digitalWrite(LED_PIN, LOW); 
    }
}