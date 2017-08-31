package com.buylist.solomakha.buylistapp.dagger.component;

import com.buylist.solomakha.buylistapp.dagger.module.TestDbModule;
import com.buylist.solomakha.buylistapp.mvp.models.BasketModelTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestDbModule.class})
public interface TestDiComponent
{
    void inject(BasketModelTest comp);
}
