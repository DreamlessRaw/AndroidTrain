# Android

## 安装

傻瓜式安装,参考：[下载并安装 Android Studio (google.cn)](https://developer.android.google.cn/codelabs/basic-android-kotlin-compose-install-android-studio?hl=zh-cn#0)

## 创建项目

<img src="C:\Users\Asura\Desktop\Android培训\创建项目-01.png" alt="image-20230626101448326"  />

<img src="C:\Users\Asura\Desktop\Android培训\创建项目-02.png"  />

![](C:\Users\Asura\Desktop\Android培训\创建项目-03.png)

## 结构预览

![](C:\Users\Asura\Desktop\Android培训\目录结构.png)

## 生命周期

![](C:\Users\Asura\Desktop\Android培训\生命周期.png)

![](C:\Users\Asura\Desktop\Android培训\生命周期-02.png)

## SharedPreferences(共享存储)

### 初始化

```java
// Activity中
SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
// Context中,一般使用这种
SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("SHAREDPREFERENCES", Context.MODE_PRIVATE);
```

### 写入

```
 // 获取Editor
 SharedPreferences.Editor editor = sharedPreferences.edit();
 // 写入数据
 editor.putString(KEY, "测试");
 // 提交
 editor.apply();
```

### 读取

```java
String content = sharedPreferences.getString(KEY, "");
```

## ListView

### activity_my_list.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MyListActivity">

    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

### MyListActivity.java

```java
public class MyListActivity extends AppCompatActivity {

    private static final String TAG = "MyListActivity";
    private ListView listView;
    private MyListAdapter adapter;
    private List<IdNameModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        listView = findViewById(R.id.listView);
        list = IdNameModel.mockData();
        adapter = new MyListAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MyListActivity.this, list.get(i).getName(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
```

### activity_my_list_item.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="horizontal">

    <TextView
        android:id="@+id/tv_id"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1" />

    <TextView
        android:id="@+id/tv_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1" />
</LinearLayout>
```

### MyListAdapter.java

```java
public class MyListAdapter extends BaseAdapter {
    private static final String TAG = "MyListAdapter";
    private List<IdNameModel> items;
    private MyListActivity activity;

    public MyListAdapter(MyListActivity activity, List<IdNameModel> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        IdNameModel item = items.get(position);

        view = LayoutInflater.from(activity).inflate(R.layout.activity_my_list_item, viewGroup, false);
        TextView id = view.findViewById(R.id.tv_id);
        TextView name = view.findViewById(R.id.tv_name);
        id.setText(String.valueOf(item.getId()));
        name.setText(item.getName());

//        name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(activity, item.getName(), Toast.LENGTH_LONG).show();
//                Log.d(TAG, "onClick: " + item.getName());
//            }
//        });

        return view;
    }
}
```

### IdNameModel.java

```java
public class IdNameModel {

    public IdNameModel() {
    }

    public IdNameModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private long id;
    private String name;


    public static List<IdNameModel> mockData() {
        List<IdNameModel> list = new ArrayList<>();
        for (long i = 1; i <= 100; i++) {
            list.add(new IdNameModel(i, String.format("第 %s 条数据", i)));
        }
        return list;
    }
}
```

## ImageView

### XML方式加载(SRC属性)

```xml
    <ImageView
        android:id="@+id/iv_01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/textview_border" />
```

### 代码加载方式

```java
ImageView imageView = findViewById(R.id.iv_01);
// 加载资源文件Resource
imageView.setImageResource(R.drawable.asura);
// assets目录文件
try {
            InputStream inputStream = getAssets().open("asura.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
        }
```

### 加载网络图片

- HttpURLConnection

  ```java
  	private ImageView mImageView;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          // ImageView
          mImageView = findViewById(R.id.iv_01);
          new Thread(new Runnable() {
              @Override
              public void run() {
                  setImage();
              }
          }).start();
  
      }
  ```

  ```java
      private Handler mHandler = new Handler(new Handler.Callback() {
          @Override
          public boolean handleMessage(@NonNull Message message) {
  
              Bitmap bitmap = (Bitmap) message.obj;
              mImageView.setImageBitmap(bitmap);
              return false;
          }
      });
  
  private void setImage() {
          try {
              URL url = new URL("https://imglf3.lf127.net/img/UmVzdmpRd3BkZU9HT2xoWkFnRWJNU0FLcEhLQys3cE5zd1dVZmxPdnRTQWRyR1VlQ2ppRXZ3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.setRequestMethod("GET");
              connection.setConnectTimeout(3000);
              int code = connection.getResponseCode();
              if (code == 200) {
                  InputStream inputStream = connection.getInputStream();
                  //使用工厂把网络的输入流生产Bitmap
                  Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                  // 使用Handler方式
                  Message msg = new Message();
                  msg.what = 0;
                  msg.obj = bitmap;
                  mHandler.sendMessage(msg);
  //                // 需要回到UI主线程,也可以使用Handler
  //                this.runOnUiThread(new Runnable() {
  //                    @Override
  //                    public void run() {
  //                        mImageView.setImageBitmap(bitmap);
  //                    }
  //                });
                  inputStream.close();
  
              } else {
                  this.runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                      }
                  });
              }
          } catch (Exception e) {
              e.printStackTrace();
              this.runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
          }
  
      }
  
  ```

  

- 插件方式(com.github.bumptech.glide)

  ```
  // build.gradle(Module:app)
  dependencies {
  
      implementation "com.github.bumptech.glide:glide:4.15.1" // 增加插件包
      
  }
  ```

  ```java
   // 使用
  ImageView imageView = findViewById(R.id.iv_01);
  Glide.with(this)
  .load("https://imglf3.lf127.net/img/UmVzdmpRd3BkZU9HT2xoWkFnRWJNU0FLcEhLQys3cE5zd1dVZmxPdnRTQWRyR1VlQ2ppRXZ3PT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg")
                  .into(imageView);
  ```

  
