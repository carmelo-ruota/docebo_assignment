package com.docebo.assignment.data

import com.docebo.assignment.data.model.Data

interface CatalogRepository {
    fun setName(name : String)
    fun setType(type : String)
    fun search(result : (Data) -> Unit, error : (String) -> Unit)
    fun hasNext() : Boolean
    fun getNext(result : (Data) -> Unit, error : (String) -> Unit)
}