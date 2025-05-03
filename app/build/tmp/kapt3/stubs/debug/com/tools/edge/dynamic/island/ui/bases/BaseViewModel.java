package com.tools.edge.dynamic.island.ui.bases;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0014J\u001c\u0010#\u001a\b\u0012\u0004\u0012\u0002H%0$\"\u0004\b\u0000\u0010%*\b\u0012\u0004\u0012\u0002H%0&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u00a8\u0006\'"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/bases/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "bgScope", "Lkotlinx/coroutines/CoroutineScope;", "getBgScope", "()Lkotlinx/coroutines/CoroutineScope;", "setBgScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "defaultDispatchers", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefaultDispatchers", "()Lkotlinx/coroutines/CoroutineDispatcher;", "setDefaultDispatchers", "(Lkotlinx/coroutines/CoroutineDispatcher;)V", "ioDispatchers", "getIoDispatchers", "setIoDispatchers", "uiDispatchers", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "getUiDispatchers", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "setUiDispatchers", "(Lkotlinx/coroutines/MainCoroutineDispatcher;)V", "uiScope", "getUiScope", "setUiScope", "viewModelJob", "Lkotlinx/coroutines/CompletableJob;", "getViewModelJob", "()Lkotlinx/coroutines/CompletableJob;", "setViewModelJob", "(Lkotlinx/coroutines/CompletableJob;)V", "onCleared", "", "toLiveData", "Landroidx/lifecycle/LiveData;", "T", "Landroidx/lifecycle/MutableLiveData;", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public class BaseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.CompletableJob viewModelJob;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.MainCoroutineDispatcher uiDispatchers;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.CoroutineDispatcher ioDispatchers;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.CoroutineDispatcher defaultDispatchers;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.CoroutineScope uiScope;
    @org.jetbrains.annotations.NotNull()
    private kotlinx.coroutines.CoroutineScope bgScope;
    
    public BaseViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CompletableJob getViewModelJob() {
        return null;
    }
    
    public final void setViewModelJob(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CompletableJob p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.MainCoroutineDispatcher getUiDispatchers() {
        return null;
    }
    
    public final void setUiDispatchers(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.MainCoroutineDispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getIoDispatchers() {
        return null;
    }
    
    public final void setIoDispatchers(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getDefaultDispatchers() {
        return null;
    }
    
    public final void setDefaultDispatchers(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineScope getUiScope() {
        return null;
    }
    
    public final void setUiScope(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineScope getBgScope() {
        return null;
    }
    
    public final void setBgScope(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope p0) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>androidx.lifecycle.LiveData<T> toLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<T> $this$toLiveData) {
        return null;
    }
}