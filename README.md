# scanlibrary

   #使用方法
   
   #首先使用Intent来跳转
   
       Intent intent = new Intent(MainActivity.this,CaptureActivity.class); 
       startActivityForResult(intent, 100);
  
   #使用Android-onActivityResult方法来获得返回扫描到的二维码条形码数据
   
    protected void onActivityResult(int arg0, int arg1, Intent data) {
      super.onActivityResult(arg0, arg1, data); 
      /**
       * 拿到解析完成的字符串
       */
      if (data != null) {
          TextView text = (TextView) findViewById(R.id.textView1);
          text.setText(data.getStringExtra("result"));
      }
    }

  