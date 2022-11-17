package com.square.testapp.domain.employee

enum class EmployeeType {
    FULL_TIME {
        override fun getLabel(): String {
            return "Full Time"
        }

    },
    PART_TIME {
        override fun getLabel(): String {
            return "Part Time"
        }
    },
    CONTRACTOR {
        override fun getLabel(): String {
            return "Contractor"
        }
    };

    abstract fun getLabel(): String
}