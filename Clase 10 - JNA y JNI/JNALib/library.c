/*
 * library.c
 *
 *  Created on: Nov 22, 2017
 *      Author: knife
 */
#include "library.h"

LIBRARY_API double dotMultiplication(double* a, double* b, int len) {
	double result = 0;
	for(int i = 0; i< len; i++) {
		result += a[i] * b[i];
	}
	return result;
}
