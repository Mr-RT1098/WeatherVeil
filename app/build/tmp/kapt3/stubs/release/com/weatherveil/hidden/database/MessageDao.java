package com.weatherveil.hidden.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00140\u0013H\'J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0019\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\u001e"}, d2 = {"Lcom/weatherveil/hidden/database/MessageDao;", "", "countUnread", "", "currentUser", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "message", "Lcom/weatherveil/hidden/database/Message;", "(Lcom/weatherveil/hidden/database/Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpiredMessages", "cutoffTimestamp", "getAllMessages", "Landroidx/lifecycle/LiveData;", "", "getAllMessagesOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "markAllRead", "setSaved", "saved", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_release"})
@androidx.room.Dao()
public abstract interface MessageDao {
    
    @androidx.room.Query(value = "SELECT * FROM messages ORDER BY timestamp ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.weatherveil.hidden.database.Message>> getAllMessages();
    
    @androidx.room.Query(value = "SELECT * FROM messages ORDER BY timestamp ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllMessagesOnce(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.weatherveil.hidden.database.Message>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.weatherveil.hidden.database.Message message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.weatherveil.hidden.database.Message message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.weatherveil.hidden.database.Message message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM messages WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        DELETE FROM messages \n        WHERE isSaved = 0 \n        AND timestamp < :cutoffTimestamp\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteExpiredMessages(long cutoffTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE messages SET isSaved = :saved WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setSaved(long id, boolean saved, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE messages SET isRead = 1 WHERE senderId != :currentUser")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAllRead(@org.jetbrains.annotations.NotNull()
    java.lang.String currentUser, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM messages WHERE isRead = 0 AND senderId != :currentUser")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countUnread(@org.jetbrains.annotations.NotNull()
    java.lang.String currentUser, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}