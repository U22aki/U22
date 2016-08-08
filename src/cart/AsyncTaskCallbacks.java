package cart;
/**
 * AsyncScraping用インターフェース
 */
public interface AsyncTaskCallbacks {
	public void onTaskFinished();//終了
	public void onTaskCancelled();//キャンセル
}
