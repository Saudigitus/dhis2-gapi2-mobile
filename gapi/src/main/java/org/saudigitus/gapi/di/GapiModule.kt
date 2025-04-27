package org.saudigitus.gapi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.dhis2.commons.network.NetworkUtils
import org.dhis2.commons.resources.ResourceManager
import org.hisp.dhis.android.core.D2
import org.saudigitus.gapi.data.local.FormRepository
import org.saudigitus.gapi.data.local.ProgramRepository
import org.saudigitus.gapi.data.local.TeiRepository
import org.saudigitus.gapi.data.local.repository.FormRepositoryImpl
import org.saudigitus.gapi.data.local.repository.ProgramRepositoryImpl
import org.saudigitus.gapi.data.local.repository.TeiRepositoryImpl
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GapiModule {
    @Provides
    @Singleton
    fun providesNetworkUtils(
        @ApplicationContext context: Context,
    ): NetworkUtils = NetworkUtils(context)

    @Provides
    @Singleton
    fun providesTEICardMapper(
        @ApplicationContext context: Context,
        resourcesManager: ResourceManager,
    ) = TEICardMapper(context, resourcesManager)

    @Provides
    @Singleton
    fun providesTeiRepository(
        d2: D2,
        networkUtils: NetworkUtils,
    ): TeiRepository = TeiRepositoryImpl(d2, networkUtils)

    @Provides
    @Singleton
    fun providesProgramRepository(
        d2: D2,
        teiRepository: TeiRepository,
    ): ProgramRepository = ProgramRepositoryImpl(d2, teiRepository)

    @Provides
    @Singleton
    fun providesFormRepository(
        d2: D2,
    ): FormRepository = FormRepositoryImpl(d2)
}
