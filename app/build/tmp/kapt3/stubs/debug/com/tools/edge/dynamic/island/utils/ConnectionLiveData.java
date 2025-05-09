package com.tools.edge.dynamic.island.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/tools/edge/dynamic/island/utils/ConnectionLiveData;", "Landroidx/lifecycle/LiveData;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "cm", "Landroid/net/ConnectivityManager;", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "validNetworks", "", "Landroid/net/Network;", "checkValidNetworks", "", "createNetworkCallback", "doesNetworkHaveInternet", "socketFactory", "Ljavax/net/SocketFactory;", "onActive", "onInactive", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public final class ConnectionLiveData extends androidx.lifecycle.LiveData<java.lang.Boolean> {
    private android.net.ConnectivityManager.NetworkCallback networkCallback;
    @org.jetbrains.annotations.NotNull()
    private final android.net.ConnectivityManager cm = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<android.net.Network> validNetworks = null;
    
    public ConnectionLiveData(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    private final void checkValidNetworks() {
    }
    
    @java.lang.Override()
    protected void onActive() {
    }
    
    @java.lang.Override()
    protected void onInactive() {
    }
    
    private final android.net.ConnectivityManager.NetworkCallback createNetworkCallback() {
        return null;
    }
    
    private final boolean doesNetworkHaveInternet(javax.net.SocketFactory socketFactory) {
        return false;
    }
}