package third

import utils.ThirdAlgorithm

sealed class TeamEvaluationCriteria

data class CriteriaMain(
//    val title: String,
    val weight: Int,
) : TeamEvaluationCriteria()

data class CriteriaK(
    val L: ThirdAlgorithm.L,
    val D: Double,
    val weight: Int,
) : TeamEvaluationCriteria()
