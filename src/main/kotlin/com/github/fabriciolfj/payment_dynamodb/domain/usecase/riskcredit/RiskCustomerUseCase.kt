package com.github.fabriciolfj.payment_dynamodb.domain.usecase.riskcredit

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CustomerResultRisk

interface RiskCustomerUseCase {

    fun execute(risk: CustomerResultRisk)
}