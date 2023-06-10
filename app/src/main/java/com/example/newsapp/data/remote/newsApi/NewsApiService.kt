package com.example.newsapp.data.remote.newsApi

import android.content.Context
import com.example.newsapp.R
import com.example.newsapp.data.remote.newsApi.response.EverythingNewsResponse
import com.example.newsapp.data.remote.newsApi.response.TopHeadlinesNewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    companion object {
        fun create(context: Context): NewsApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.news_api_base_url))
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

            return retrofit.create(NewsApiService::class.java)
        }
    }

    /**
     * 検索条件に一致したニュースを取得する
     */
    @GET("/v2/everything")
    suspend fun getEverythingNews(
        @Query("api_key") apiKey: String,
        @Query("q") q: String,
        @Query("page") page: Int,
    ): Response<EverythingNewsResponse?>

    /**
     * カテゴリ毎に最新ニュースを取得する
     */
    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesNews(
        @Query("api_key") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("page") page: Int,
    ): Response<TopHeadlinesNewsResponse?>
}