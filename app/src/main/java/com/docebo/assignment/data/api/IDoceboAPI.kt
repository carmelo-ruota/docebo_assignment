package com.docebo.assignment.data.api

import com.docebo.assignment.data.model.Data
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IDoceboAPI {
    @GET("catalog")
    fun getCatalog(@Query("type[]") type: String, @Query("search_text") search_text: String,@Query("page") page: Int): Observable<BaseResponse<Data>>

}