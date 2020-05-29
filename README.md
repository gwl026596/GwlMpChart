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

 <com.gwl.chartlibrary.view.LineChartView
        android:id="@+id/lineChartView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="10dp"/>
       
	
Step 4.填充数据
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
