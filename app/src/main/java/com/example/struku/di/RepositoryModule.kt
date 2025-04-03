package com.example.struku.di

import android.content.Context
import com.example.struku.data.parser.ReceiptParser
import com.example.struku.data.recognition.AdvancedImagePreprocessor
import com.example.struku.data.recognition.MkitOcrEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMkitOcrEngine(@ApplicationContext context: Context): MkitOcrEngine {
        return MkitOcrEngine(context)
    }

    @Provides
    @Singleton
    fun provideAdvancedImagePreprocessor(mkitOcrEngine: MkitOcrEngine): AdvancedImagePreprocessor {
        return AdvancedImagePreprocessor(mkitOcrEngine)
    }

    @Provides
    @Singleton
    fun provideReceiptParser(advancedImagePreprocessor: AdvancedImagePreprocessor): ReceiptParser {
        return ReceiptParser(advancedImagePreprocessor)
    }
}
