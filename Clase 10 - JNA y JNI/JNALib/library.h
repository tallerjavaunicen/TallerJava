/*
 * library.h
 *
 *  Created on: Nov 22, 2017
 *      Author: knife
 */

#ifndef LIBRARY_H_
#define LIBRARY_H_

#ifdef _WIN32
#ifdef CUSPLIBRARY_EXPORTS
#define LIBRARY_API __declspec(dllexport)
#else
#define LIBRARY_API __declspec(dllimport)
#endif
#else
#define LIBRARY_API
#endif

LIBRARY_API double dotMultiplication(double* a, double* b, int len);

#endif /* LIBRARY_H_ */
