package xstar.com.commons;

import android.app.Activity;
import android.os.Bundle;

import xstar.com.library.commons.view.MarqeeTextView;

/**
 * @author xstar on 2016/2/24.
 */
public class MarqeeTextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MarqeeTextView marqeeTextView = new MarqeeTextView(this);
        marqeeTextView.setText("赵客缦胡缨，吴钩霜雪明。银鞍照白马，飒沓如流星。" +
                "十步杀一人，千里不留行。事了拂衣去，深藏身与名。" +
                "闲过信陵饮，脱剑膝前横。将炙啖朱亥，持觞劝侯嬴。" +
                "三杯吐然诺，五岳倒为轻。眼花耳热后，意气素霓生。" +
                "救赵挥金槌，邯郸先震惊。千秋二壮士，烜赫大梁城。");
        marqeeTextView.setColorId(R.color.red);
        marqeeTextView.setSpeed(200);
        marqeeTextView.setTextSize(25);
        setContentView(marqeeTextView);
        marqeeTextView.setMarqeeListener(new MarqeeTextView.MarqeeListener() {
            @Override
            public void marqeeFinish(MarqeeTextView marqeeTextView) {
                marqeeTextView.repeat();
            }
        });
    }
}
