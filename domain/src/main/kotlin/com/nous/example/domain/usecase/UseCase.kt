package com.nous.example.domain.usecase

sealed interface UseCase<in I, out O>

/**
 * Base use case for use cases with synchronous invoke method.
 *
 * @see UseCase
 * @see SuspendUseCase
 */
interface SynchronousUseCase<in I, out O> : UseCase<I, O> {
    operator fun invoke(input: I): O
}

/**
 * Base use case for use cases which need `suspend` invoke method.
 *
 * @see UseCase
 * @see SynchronousUseCase
 */
interface SuspendUseCase<in I, out O> : UseCase<I, O> {
    suspend operator fun invoke(input: I): O
}

/**
 * Syntax sugar extension for calling synchronous use case without an input.
 *
 * @see SynchronousUseCase
 * @see UseCase
 */
operator fun <O> SynchronousUseCase<Unit, O>.invoke(): O = invoke(Unit)

/**
 * Syntax sugar extension for calling suspend use case without an input.
 *
 * @see SuspendUseCase
 * @see UseCase
 */
suspend operator fun <O> SuspendUseCase<Unit, O>.invoke(): O = invoke(Unit)