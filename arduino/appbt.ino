#include <SoftwareSerial.h>
SoftwareSerial bluetooth(6,7);//(RX,TX)電腦端
String command="";
int store = 1;

#define SER0 8  //串行數據輸入
#define SCK0 9  //輸入到移位寄存器
#define RCK0 12  //上升沿是串行輸入，並行輸出並鎖存
unsigned char a[64]={
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01};//前
unsigned char b[64]={
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,
  0X80,0X80,0X80,0X80,0X80,0X80,0X80,0X80,};//後
unsigned char c[64]={
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0X00,0X00,0X00,0X00,0X00,0X00,0X00,};//左
unsigned char d[64]={
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0XFF,};//右  
unsigned char e[64]={
  0XFF,0XFF,0XFF,0XFF,0XFF,0XFF,0XFF,0XFF,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,};//up
unsigned char f[64]={
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0X00,0X00,0X00,0X00,0X00,0X00,0X00,0X00,
  0XFF,0XFF,0XFF,0XFF,0XFF,0XFF,0XFF,0XFF,};//down
unsigned char _up[64]={
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X01,0X01,0X00,
  0X00,0X00,0X00,0X01,0X01,0X01,0X01,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,};//上
unsigned char _down[64]={
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X01,0X01,0X01,0X01,0X01,0X01,0X01,0X01,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X01,0X01,0X00,
  0X00,0X00,0X00,0X01,0X01,0X01,0X01,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,};//下
unsigned char _right[64]={
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X01,0X01,0X01,0X01,0X01,0X01,0X00,
  0X00,0X00,0X01,0X01,0X00,0X00,0X00,0X00,
  0X00,0X01,0X01,0X00,0X00,0X00,0X00,0X00,
  0X01,0X01,0X01,0X01,0X01,0X01,0X00,0X00,
  0X01,0X00,0X01,0X00,0X00,0X01,0X00,0X00,
  0X00,0X00,0X01,0X00,0X00,0X01,0X00,0X00,
  0X00,0X00,0X01,0X01,0X01,0X01,0X00,0X00,};//右
unsigned char _left[64]={
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X01,0X01,0X01,0X01,0X01,0X01,0X00,
  0X00,0X00,0X01,0X01,0X00,0X00,0X00,0X00,
  0X00,0X01,0X01,0X00,0X00,0X00,0X00,0X00,
  0X01,0X01,0X01,0X01,0X01,0X01,0X00,0X00,
  0X01,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X00,0X01,0X01,0X00,0X00,0X00,
  0X00,0X00,0X01,0X01,0X01,0X01,0X00,0X00,};//右
  
unsigned char save[64]={0X00};
String one_to_eight[]={"1","2","3","4","5","6","7","8"};
String eight_to_one[]={"8","7","6","5","4","3","2","1"};

int num = 7;
char test = 1;
int select = 0,set = 0;
void setup() {
  Serial.begin(9600);
  bluetooth.begin(38400);
  
  pinMode(SER0,OUTPUT);
  pinMode(SCK0,OUTPUT);
  pinMode(RCK0,OUTPUT);
}

void loop() {
    
    while(bluetooth.available())
    {
      char input_word = bluetooth.read();
      Serial.println(input_word);
      command += input_word;
      delay(10);
      test=input_word;    
      }
    switch(test)
    {
      case 'a':
            for(int i=0; i<64 ; i++)
            {
              save[i]=a[i];
            }
            select = 1;
            command="";
            store = 1;
            set++;
            break;
      case 'b':
            for(int i=0; i<64 ; i++)
            {
              save[i]=b[i];
            }
            select = 2;
            command="";
            store = 1;
            set++;
            break;
      case 'c':
            for(int i=0; i<64 ; i++)
            {
              save[i]=c[i];
            }
            select = 3;
            command="";
            store = 1;
            set++;
            break;
      case 'd':
            for(int i=0; i<64 ; i++)
            {
              save[i]=d[i];
            } 
            select = 4;
            command="";
            store = 1;
            set++;
            break;
      case 'e':
            for(int i=0; i<64 ; i++)
            {
              save[i]=e[i];
            } 
            select = 5;
            command="";
            store = 1;
            set++;
            break;
       case 'f':
            for(int i=0; i<64 ; i++)
            {
              save[i]=f[i];
            } 
            select = 6;
            command="";
            store = 1;
            set++;
            break;
       case 'r' :
           set=0;
           bluetooth.println("{");
           bluetooth.println("");
           bluetooth.println("}");
           for(int i=0; i<64 ; i++)
            {
              save[i]=0x00;
            }           
       break;  
       case 'u':
           acw(_up,64);
       break;
       case 'n':
           acw(_down,64);
       break; 
       case 't':
           acw(_right,64);
       break; 
       case 'l':
           acw(_left,64);
       break;
       
    }
    view();
}

void view()
{  
  int count=0,seven=7;
  Serial.println(set);
  if(set>0)
  {
    bluetooth.println("{");
  }
  switch(select)
  {
    case 1:
     bluetooth.print("前面:");
     for(int i=0;i<8;i++)
      {
        if(command == one_to_eight[i])
          {
            for(int z=0;z<8;z++)
            {
              for(int k=0;k<8;k++)
              {
                save[z*8+k]=a[z*8+k]<<i;
              }  
              store = i+1;
            } 
          }
      } 
    bluetooth.print("第");  
    bluetooth.print(store);
    bluetooth.print("層");
    break;
 
    case 2:
     bluetooth.print("後面:");
     for(int i=0;i<8;i++)
      {
        if(command == one_to_eight[i])
          {
            for(int z=0;z<8;z++)
            {
              for(int k=0;k<8;k++)
              {
                save[z*8+k]=b[z*8+k]>>i;
              }  
            } 
            store = i+1;        
          }
      }
    bluetooth.print("第");  
    bluetooth.print(store);
    bluetooth.print("層");
    break;

    case 3:
       bluetooth.print("左邊:");
       for(int i=0;i<8;i++)
      {
        if(command == one_to_eight[i])
          {
            for(int z=0;z<8;z++)
            {
              for(int k=0;k<8;k++)
              {
                save[z*8+k]=0x00;
              }
              save[z*8+i]=0xff;  
            } 
            store = i+1;          
          }
      } 
    bluetooth.print("第");  
    bluetooth.print(store);
    bluetooth.print("層");
    break;
    
    case 4:
      bluetooth.print("右邊:");
      for(int i=0;i<8;i++)
      {
        if(command == eight_to_one[i])
          {
            for(int z=0;z<8;z++)
            {
              for(int k=0;k<8;k++)
              {
                save[z*8+k]=0x00;
              }
              save[z*8+i]=0xff;  
            }
            store = count+seven+1; 
          }
          count++;
          seven-=2;
       }
    bluetooth.print("第");  
    bluetooth.print(store);
    bluetooth.print("層");
    count=0;
    seven=7;   
    break;
    
    case 5:
     bluetooth.print("上面:");
     for(int i=0;i<8;i++)
      {
        if(command == one_to_eight[i])
          {
            for(int z=0;z<8;z++)
            {
              for(int k=0;k<8;k++)
              {
                save[z*8+k]=0x00;
                save[i*8+k]=0xff;  
              }          
            }
            store = i+1;           
          }
      }
    bluetooth.print("第");  
    bluetooth.print(store);
    bluetooth.print("層");
    break;
    
    case 6:
    bluetooth.print("下面:");
    for(int i=0;i<8;i++)
      {
        if(command == eight_to_one[i])
          {
            for(int z=0;z<8;z++)
            {
              for(int k=0;k<8;k++)
              {
                save[z*8+k]=0x00;
                save[i*8+k]=0xff;  
              }          
            }
            store = count+seven+1;         
          }
          count++;
          seven-=2;
      }  
    bluetooth.print("第");  
    bluetooth.print(store);
    bluetooth.print("層");
    count=0;
    seven=7;
    break;    
  }
  if(set>0)
  {
    bluetooth.println("}");   
  }
  frame(save,50);
  command="";
  
}




void storey(char *a)//層填充函數，控制某層燈點亮方式    
{
  char i,j,num;
  for(i=0;i<8;i++)
  {
    num=a[i];    //將數組中數輸入寄存器
    for(j=0;j<8;j++) //串行數據輸入
    {
      if(num&0x80)
        digitalWrite(SER0,1);   // SER串行輸入端口
      else
        digitalWrite(SER0,0);
      digitalWrite(SCK0,0);     //上升沿，輸入到移位寄存器
      delayMicroseconds(1);   
      digitalWrite(SCK0,1);   
      num<<=1;
    }
  
  }
}
void frame(char *a,char v)//一幀,a是一幀編碼起始地址
                      //一個畫面，v表示一幀畫面掃描的次數
            // 可以看作這幀顯示的時間
{
  char i,j,num;  // 有符號定義
  while(v--)
  { 
    num=0x01;
    for(i=0;i<8;i++) //層數層控制，選通某一層，
                         //使得第1層到第8層，依次點亮
    { 
      num<<=i;
      digitalWrite(RCK0,0);
      for(j=0;j<8;j++) //串行數據輸入
      {
        digitalWrite(SER0,0);
        delayMicroseconds(1);
        digitalWrite(SCK0,0);   //上升沿，輸入到移位寄存器
        delayMicroseconds(1); 
        digitalWrite(SCK0,1);   
      }
      for(j=0;j<8;j++) //串行數據輸入
      {
        if(num&0x80)
          digitalWrite(SER0,1);  // SER串行輸入端口
        else
          digitalWrite(SER0,0);
        
        digitalWrite(SCK0,0);   //上升沿，輸入到移位寄存器
        delayMicroseconds(1);   
        digitalWrite(SCK0,1); 
        num<<=1;
      }
      storey(a+i*8);//層填充函數，控制某層燈點亮方式
      digitalWrite(RCK0,1);
      num=0x01;     
      delay(1);   // 層顯示時間
    }
  
  } 
}
void acw(char *rec1,int arraysize){
unsigned char b[64]={0},b_1[64]={0};
unsigned char c[64]={0},data[64]={0},data2[64]={0};
unsigned char i,k;
int p=0;
int y=0,g=0;
 for (int i = 0; i < arraysize; i++)
    {
        b[i] = rec1[i];
    }
    frame(b,10);
   for(int x=0;x<28;x++){
    for(int z=0;z<8;z++)
    {
      for(int i=7;i>0;i--)
        {
        b[z*8+i]=b[z*8+(i-1)];
        }        
        if(y>0){
        c[z*8+7]<<=1;
        }
        c[z*8+7]+=b[z*8+7];
    }
    y++;
    if(x>6)
      {
        for(int k=0;k<8;k++)
        {
          for(int d=0;d<7;d++)
            { 
            data[k*8+d]=data[k*8+(d+1)];
            }
        if((c[k*8+7]&0x80)==0x80)
            {
            data[k*8+7]=0x80;
            }
          else
            {
            data[k*8+7]=0x00;
            }
            if(g=0)
            {
              for(int d=0;d<7;d++)
              { 
              data[k*8+d]=data[k*8+(d+1)];
              }
            }
            data2[k*8]>>=1; 
            data2[k*8]-=data[k*8];      
        }
      }
      /*
      if(x>7)
      {
         for (int i = 0; i < arraysize; i++)
          {
           b[i]= b_1[i];
          }  
      }*/
if(x<6)
 {
 frame(b,5);
 }
 if((x>0)&&(x<12))
 {
    frame(c,5);
 }
 if((x>7)&&(x<19))
      {
    frame(data,5);
      }
 if(x>14)
 {   
    frame(data2,5);
 }    
  }
}
