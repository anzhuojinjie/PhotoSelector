


# PhotoSelector

高仿微信图片选择器，可以配置单张，多张，布局的颜色等，高度可定制化

看下效果图：

![enter description here][1]![enter description here][2]![enter description here][3]

# 使用方法

step1：在项目gradle根目录下添加

```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
step2：在app的gradle根目录下添加

``` 
dependencies {
    compile 'com.github.anzhuojinjie:PhotoSelector:v1.0'
}
```
不要忘记添加权限

``` 
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

具体使用只需要调用下面的方法

```
    private void selectPic(){
        ImageConfig imageConfig
                = new ImageConfig
                .Builder(
                // GlideLoader 可用自己用的缓存库
                new GliderLoader())
                // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                .steepToolBarColor(getResources().getColor(R.color.title_bg))
                // 标题的背景颜色 （默认黑色）
                .titleBgColor(getResources().getColor(R.color.title_bg))
                // 提交按钮字体的颜色  （默认白色）
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                // 标题颜色 （默认白色）
                .titleTextColor(getResources().getColor(R.color.white))
//                .singleSelect()
                // 开启多选   （默认为多选）  (单选 为 singleSelect)
//                .mutiSelect()
////                        .crop()
//                // 多选时的最大数量   （默认 9 张）
                .mutiSelectMaxSize(9)
                // 已选择的图片路径
                .pathList(mPathList)
                // 拍照后存放的图片路径（默认 /temp/picture）
                .filePath("/ImageSelector/Pictures")
                // 开启拍照功能 （默认开启）
//                .showCamera()
                .requestCode(500)
                .build();
        ImageSelector.open(MainActivity.this, imageConfig);   // 开启图片选择器
    }
```

重写onActivityResult方法

``` 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case 500:
                    List<String> mList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
                    StringBuffer sb = new StringBuffer();
                    for(String path : mList){
                        sb.append(path);
                        sb.append("\n");
                    }
                    tvPath.setText(sb.toString());
                    break;
                default:
                    break;
            }
        }
    }
```
如有疑问欢迎加入
群名称：安卓开发交流群
群   号：599329462

