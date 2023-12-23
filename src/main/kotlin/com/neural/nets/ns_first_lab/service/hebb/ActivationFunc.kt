package com.neural.nets.ns_first_lab.service.hebb

class ActivationFunc{

    companion object {

        fun binary(x: Int): Int {
            return if (x > 0) 1 else 0
        }

        fun bipolar(x: Int): Int {
            return if (x > 0) 1 else -1
        }
    }
}
