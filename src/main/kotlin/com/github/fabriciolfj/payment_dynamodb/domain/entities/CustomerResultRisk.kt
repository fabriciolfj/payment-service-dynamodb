package com.github.fabriciolfj.payment_dynamodb.domain.entities

data class CustomerResultRisk(val status: Status, val identifier: String) {

    fun isApproved() = this.status == Status.APPROVED
}