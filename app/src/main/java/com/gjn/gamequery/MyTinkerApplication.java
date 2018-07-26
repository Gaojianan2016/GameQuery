package com.gjn.gamequery;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author gjn
 * @time 2018/7/26 16:42
 */

public class MyTinkerApplication extends TinkerApplication {
    public MyTinkerApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.gjn.gamequery.MyTinkerApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
