package com.github.fabriciolfj.payment_dynamodb.domain.usecase.transaction

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CreditCardPayment

interface TransactionNotifyRequestApproveAdapter {
    
    fun process(entity: CreditCardPayment)
}