# BKnifeDemo
通过注解在编译时期生成内部类,完成findviewbyid的功能
使用之前调用 BKnife.inject(Activity),BKnife.inject(View),BKnife.inject(HostClass,View)完成绑定;

可以使用单个空控件绑定,例如:
@BindView(R.id.tv_container)
    TextView mTvContainer;
    
也可以使用多个相同类型控件绑定到数组:
@BindViews({R.id.tv_binds_container1, R.id.tv_binds_container2, R.id.tv_binds_container3, R.id.tv_binds_container4})
    TextView[] mTvConArray;
    
可以设置监听事件
 @OnClick(R.id.bt_click1)
    public void onBt1Click() {
      Toast.makeText(this, "按钮1被点击了", Toast.LENGTH_SHORT).show();
  }
  
编译后生成内部类,通过调用BKnife的inject方法,反射生成内部类的实例,内部类集成IBind接口,可以直接调用inject方法.

因为不想导入compile 'com.squareup:javapoet:1.7.0'工程,手动拼接了代码.
码没有导入 compile 'com.google.auto.service:auto-service:1.0-rc2'所以需要在main下创建javax.annotation.processing.Processor文件来指定编译时候的处理器
