package com.mvvm.cvapplication.util

interface BaseMapper<IINPUT,OUTPUT>{
    fun convert (input : IINPUT): OUTPUT
}