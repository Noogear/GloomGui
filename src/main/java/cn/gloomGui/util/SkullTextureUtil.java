package cn.gloomGui.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.maven.model.Profile;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class SkullTextureUtil {

    protected static final Cache<String, Profile> CACHE = CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.HOURS).build();
    protected static final Executor EXECUTOR = CompletableFuture.completedFuture(null).defaultExecutor();
    private static final String TEXTURE_URL = "http://textures.minecraft.net/texture/";


    public static PlayerProfile getProfileByString(String value) {
        return null;
    }


}
