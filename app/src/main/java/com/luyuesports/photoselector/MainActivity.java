package com.luyuesports.photoselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.luyuesports.joeyphotoselector.GliderLoader;
import com.luyuesports.joeyphotoselector.ImageConfig;
import com.luyuesports.joeyphotoselector.ImageSelector;
import com.luyuesports.joeyphotoselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvPath;
    private List<String> mPathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPath = ((TextView) findViewById(R.id.tv_path));
        mPathList = new ArrayList<>();
    }

    public void btnClick(View view) {
        switch (view.getId()){
            case R.id.btnSelectPic:
                selectPic();
                break;
            default:
                break;
        }
    }

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
}
