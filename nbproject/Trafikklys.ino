const int Nordbutton = 2;
const int Vestbutton = 3;
const int NordOvergangRed = 4;
const int NordOvergangGreen = 5;
const int VestOvergangRed = 6;
const int VestOvergangGreen = 7;
const int NordRed = 8;
const int NordYellow = 9;
const int NordGreen = 10;
const int VestRed = 11;
const int VestYellow = 12;
const int VestGreen = 13;


boolean ending = false;
boolean prepareState = true;
boolean VestbuttonClick = false;
boolean NordbuttonClick = false;

int VestbuttonState = 0;
int NordbuttonState = 0;
int interval = 1000;
unsigned long startTime = 0;
int state = 0;


void setup() {
  pinMode(Vestbutton,INPUT);
  pinMode(Nordbutton,INPUT);
  pinMode(VestOvergangRed,OUTPUT);
  pinMode(VestOvergangGreen,OUTPUT);
  pinMode(NordOvergangRed,OUTPUT);
  pinMode(NordOvergangGreen,OUTPUT);
  pinMode(VestRed,OUTPUT);
  pinMode(VestYellow,OUTPUT);
  pinMode(VestGreen,OUTPUT);
  pinMode(NordRed,OUTPUT);
  pinMode(NordYellow,OUTPUT);
  pinMode(NordGreen,OUTPUT);
  Serial.begin(9600);
}

void loop() {
  int VestbuttonState = digitalRead(Vestbutton);
  int NordbuttonState = digitalRead(Nordbutton);
  
  if(VestbuttonState == LOW) {
    VestbuttonClick = true;
  }
  
  if(NordbuttonState == LOW) {
    NordbuttonClick = true;
  }
  
  if ( interval + startTime < millis()) {
    state += 1;
    startTime = millis();
  }
  if ( state == 0) {
    // Nord Rød && Vest Rød && overgang Rød
    digitalWrite(NordRed, HIGH);
    digitalWrite(VestRed, HIGH);
    digitalWrite(NordOvergangRed, HIGH);
    digitalWrite(VestOvergangRed, HIGH);
  }
  
  if (state == 2) {
    // NordGul
    digitalWrite(NordYellow, HIGH);
  }
  
  if(state == 4) {
    // Nord Grønn && LOW(Nord Rød)
    digitalWrite(NordYellow, LOW);
    digitalWrite(NordRed, LOW);
    digitalWrite(NordGreen, HIGH);
  }
  
  if(state == 14) {
    // // LOW(Nord Grønn) && HIGH(Nord Gul)
    digitalWrite(NordGreen, LOW);
    digitalWrite(NordYellow, HIGH);
  }
  
  if(state == 16) {
    // LOW(Nord Gul) && HIGH(Nord Rød)
    digitalWrite(NordRed, HIGH);
    digitalWrite(NordYellow, LOW);
    if(NordbuttonClick == true){
      state = 1000;
    }

  }
  
  
  
  
  if (state == 18) {
    // NordGul
    digitalWrite(VestYellow, HIGH);
  }
  
  if(state == 20) {
    // Nord Grønn && LOW(Nord Rød)
    digitalWrite(VestYellow, LOW);
    digitalWrite(VestRed, LOW);
    digitalWrite(VestGreen, HIGH);
  }
  
  if(state == 30) {
    // // LOW(Nord Grønn) && HIGH(Nord Gul)
    digitalWrite(VestGreen, LOW);
    digitalWrite(VestYellow, HIGH);
    
    if(VestbuttonClick == true){
       state = 2000;
    }
    else
    state = 0;
  }
  
  if(state == 32) {
    // LOW(Nord Gul) && HIGH(Nord Rød)
    digitalWrite(VestRed, HIGH);
    digitalWrite(VestYellow, LOW);

  }

   
    
    
    
    
    
    
    
  }
  
  
  
  
  if(interval + 
  

    
  
  
  
