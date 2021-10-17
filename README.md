
Version：[![](https://jitpack.io/v/by8023hxy/basemodule.svg)](https://jitpack.io/#by8023hxy/basemodule)
<h1 align="center">basemodule</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
basemodule是基于Android应用程序技术栈Jetpack封装的基础模块，旨在快速构建应用，提升开发效率。
</p>
</br>

## Gradle
 Add a dependency code to your **module**'s `build.gradle` file.
 ```gradle
 Step 1. Add the JitPack repository to your build file
 allprojects {
 		repositories {
 			...
 			maven { url 'https://jitpack.io' }
 		}
 	}
 	Step 2. Add the dependency
 dependencies {
 	        implementation 'com.github.by8023hxy:basemodule:Tag'
 	}
```

## 技术栈和开源库
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)+[Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow) for asynchronous.
- Koin 依赖注入框架
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.

- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern 数据仓库
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Glide](https://github.com/bumptech/glide) (https://github.com/florent37/GlidePalette) - loading images.
- Gson   操作java对象和json数据之间的相互转换



## Architecture
BasicFramework is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)

## Usage
### Activity

```kotlin
abstract class DataBindingActivity : AppCompatActivity() {

    protected abstract fun getDataBindingConfig(): DataBindingConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setObserve()
        initView()
        initData()
     }
   }

class MainActivity : DataBindingActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val dialog: ProgressDialogFragment by lazy { ProgressDialogFragment() }
    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main,BR.vm,mainViewModel)
            .addBindingParam(BR.adapter,DemoAdapter())
    }

    override fun initView() {
        mainViewModel.apply {
            getBannerList()
        }
    }

    override fun initData() {

    }

    override fun setObserve() {
        mainViewModel.isLoading().observe(this, {
            operateDialog(it)
        })
      }
    }
```

### Repository

```kotlin
inline fun <T> flowScope(crossinline request: suspend () -> BaseResponse<T>): LiveData<RequestState<T>> {
    return flow {
        "flowScope onThread=${Thread.currentThread().name}".logD(TAG)
        val response = request()
        "flowScope response=${Gson().toJson(response)}".logD(TAG)
        if (response.isSuccess()) {
            emit(RequestState.Success(response.getResponseData()))
        } else {
            emit(
                RequestState.Error(
                    ApiException(
                        response.getResponseMsg(),
                        response.getResponseCode()
                    )
                )
            )
        }
    }.onStart {
        emit(RequestState.Loading)
    }.catch {
        val apiException = getApiException(it)
        "flowScope exception==${Gson().toJson(it)}".logE()
        emit(RequestState.Error(apiException))
    }.asLiveData()
}

class AppRepository(private val remoteService: RemoteService) {
    suspend fun getBannerList(id:String) = flowScope { remoteService.getBanner() }
}
```

### ViewModel

```kotlin
class MainViewModel(private val repository: AppRepository) : LiveCoroutinesViewModel() {

    val banner: ObservableField<List<BannerInfo>> = ObservableField()

    private val _bannerList = MutableLiveData<String>()

    val bannerList=_bannerList.switchMap {
        launchOnViewModelScope { repository.getBannerList() }
    }

    fun getBannerList(id:String){
        _bannerList.value=id
    }

}
```

### Koin in Application

```kotlin
class MyApp : BaseApp() {
    companion object {
        @JvmStatic
        lateinit var CONTEXT: BaseApp

        @JvmStatic
        val shareViewModel: ShareViewModel by lazy { ShareViewModel() }
    }

    override fun onCreate() {
        super.onCreate()
        "MyApp onCreate!!!".logD()
        CONTEXT = this
        MMKV.initialize(this)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            androidFileProperties()
            modules(applicationModules)
        }
    }
}
```

### Koin module

```kotlin
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { AppRepository(get()) }
}

val httpModule = module {
     single<OkHttpClient> {
           OkHttpClient.Builder()
               .cache(Cache(File(MyApp.CONTEXT.cacheDir, "okhttp_cache"), 1024 * 1024 * 256L))
               .addInterceptor(CacheInterceptor())
               .addInterceptor(HttpLoggingInterceptor { message -> message.logD("MyHttpLog") }.apply {
                   this.level = HttpLoggingInterceptor.Level.BODY
               })    // 日志拦截器
               .addNetworkInterceptor(CacheNetworkInterceptor())
               .connectTimeout(BaseConstant.HTTP_CONNECT_TIME, TimeUnit.SECONDS)
               .readTimeout(BaseConstant.HTTP_READ_TIME, TimeUnit.SECONDS)
               .writeTimeout(BaseConstant.HTTP_WRITE_TIME, TimeUnit.SECONDS)
               .build()
       }
    single<RemoteService> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(RemoteService.BASE_URL)
            .build().create(RemoteService::class.java)
    }
}
```
## Find this library useful? :heart:
Support it by joining __[basemodule](https://github.com/by8023hxy/basemodule)__ for this repository. :star: <br>
And __[follow](https://github.com/by8023hxy)__ me for my next creations! 🤩

# Thanks to

[AndroidX](https://developer.android.google.cn/jetpack/androidx)

[Jetpack MVVM](https://developer.android.google.cn/jetpack/)

[UnPeek-LiveData](https://github.com/KunMinX/UnPeek-LiveData)

[Jetpack-MVVM-Best-Practice](https://github.com/KunMinX/Jetpack-MVVM-Best-Practice)

[AndroidUtilCodeKTX](https://github.com/lulululbj/AndroidUtilCodeKTX)

[Bindables](https://github.com/skydoves/Bindables)


