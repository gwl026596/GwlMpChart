# GwlMpChart
第一次提交图标库
使用步骤
Step 1.Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.gwl026596:GwlMpChart:1.0.0'
	}
	
Step 3.折线图使用在XML	
```
	<com.gwl.chartlibrary.view.PieChartView
        android:id="@+id/pieChartView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        />
```
Step 4.填充数据
```PieChartView pieChartView=findViewById(R.id.pieChartView);
        listData = new ArrayList<>();
        listColor = new ArrayList<>();
        listData.add(12.4f);
        //listData.add(15.4f);
        //listData.add(2.4f);
        listData.add(32.4f);
        listData.add(42.4f);
        listData.add(37.4f);
        listData.add(29.4f);

        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_E60019));
        //listColor.add(ContextCompat.getColor(this, R.color.color_F8800F));
        //listColor.add(ContextCompat.getColor(this, R.color.color_C6EE11));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_00B0F0));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_002987));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_7C1BBE));
        listColor.add(ContextCompat.getColor(this, com.gwl.chartlibrary.R.color.color_808080));
        pieChartView.setData(listData,listColor);
```
（样式如下图）
![image](https://github.com/gwl026596/GwlMpChart/blob/master/app/src/main/res/mipmap-xxhdpi/piechart.gif?raw=true)
Step 5.折线图使用在XML	
```
	<com.gwl.chartlibrary.view.BarChartView
         android:id="@+id/barChartView"
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         android:padding="10dp"/>
```
Step 6.填充数据
```
         BarChartView barChartView=findViewById(R.id.barChartView);
        ArrayList<PointF>  pointFs = new ArrayList<PointF>();
        pointFs.add(new PointF(1F, 2.7F));
        pointFs.add(new PointF(2F, 3.5F));
        pointFs.add(new PointF(3F, 3.2F));
        pointFs.add(new PointF(4F, 1.8F));
        pointFs.add(new PointF(5F, 1.5F));
        pointFs.add(new PointF(6F, 2.2F));
        pointFs.add(new PointF(7F, 5.2F));
        pointFs.add(new PointF(8F, 1.8F));
        pointFs.add(new PointF(9F, 1.5F));
        pointFs.add(new PointF(10F, 2.2F));
        pointFs.add(new PointF(11F, 5.2F));
        pointFs.add(new PointF(12F, 1.5F));
        barChartView.setData(pointFs);
```
（样式如下图）
![image](https://github.com/gwl026596/GwlMpChart/blob/master/app/src/main/res/mipmap-xxhdpi/barchart.gif?raw=true)
Step 7.折线图使用在XML	
```
	<com.gwl.chartlibrary.view.LineChartView
        android:id="@+id/lineChartView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="10dp"/>
```
Step 8.填充数据
```
        LineChartView lineChartView=findViewById(R.id.lineChartView);
        ArrayList<PointF>  pointFs = new ArrayList<PointF>();
        pointFs.add(new PointF(0.3F, 0.5F));
        pointFs.add(new PointF(1F, 2.7F));
        pointFs.add(new PointF(2F, 3.5F));
        pointFs.add(new PointF(3F, 7.2F));
        pointFs.add(new PointF(4F, 9.8F));
        pointFs.add(new PointF(5F, 13.5F));
        pointFs.add(new PointF(6F, 17.2F));
        pointFs.add(new PointF(7F, 20.5F));
        pointFs.add(new PointF(8F, 25F));
        pointFs.add(new PointF(18.6F, 29.7F));
        lineChartView.setData(pointFs);
```
（样式如下图）
![image](https://github.com/gwl026596/GwlMpChart/blob/master/app/src/main/res/mipmap-xxhdpi/linechat.gif?raw=true)

 
       
	

