package com.github.fabriciolfj.payment_dynamodb.application.entrypoint.listener.resultriskcard

import com.github.fabriciolfj.payment_dynamodb.domain.entities.CustomerResultRisk
import com.github.fabriciolfj.payment_dynamodb.domain.entities.Status

object CustomerResultRiskMapper {

    fun toEntity(dto: CustomerResultRiskDTO) : CustomerResultRisk {
        return CustomerResultRisk(
            identifier = dto.identifier,
            status = Status.toStatus(dto.status)
        )
    }
}