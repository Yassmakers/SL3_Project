package com.example.sl3verbeterd

object AccountConstants {
    // Arraylist and return the Arraylist
    fun getAccountData():ArrayList<Account>{
        // create an arraylist of type employee class
        val accountsList=ArrayList<Account>()
        val acc1=Account(1,"jeffrey@gmail.com", "jaja")
        accountsList.add(acc1)
        val acc2=Account(2,"yassine@gmail.com", "nene")
        accountsList.add(acc2)
        val acc3=Account(3,"stephan@gmail.com", "huh")
        accountsList.add(acc3)

        return accountsList
    }
}