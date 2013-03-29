<?php

class PostController extends ERestController
{
	/**
	 * Controls access to restfull requests
	 */ 
	public function filterRestAccessRules( $c )
	{
		Yii::app()->clientScript->reset(); //Remove any scripts registered by Controller Class
		Yii::app()->onException = array($this, 'onException'); //Register Custom Exception
		$c->run();
	}
	
	public function _filters()
	{
		return array();
	}
	
	public function _accessRules()
	{
		return array();
	}
	
	public function doRestList()
	{
		$data = array(
			array( "post_id" => 1, "time" => "3月3日 12時42分", "title" => "Evernoteが不正アクセス被害" ),
			array( "post_id" => 2, "time" => "3月4日 18時3分", "title" => "警戒区域で初 ストビュー撮影" ),
			array( "post_id" => 3, "time" => "3月3日 7時38分", "title" => "Twitter 年内にも株式上場か" ),
			array( "post_id" => 4, "time" => "3月3日 22時14分", "title" => "人生に影響?共有ソフトの怖さ" ),
			array( "post_id" => 5, "time" => "3月3日 7時29分", "title" => "音楽「聴き放題」続出の理由" ),
			array( "post_id" => 6, "time" => "3月4日 23時2分", "title" => "Sonyも新OSスマホ 新興国狙い" ),
			array( "post_id" => 7, "time" => "3月4日 13時0分", "title" => "Xperia Zに復活託すソニー" ),
			array( "post_id" => 8, "time" => "3月4日 11時47分", "title" => "活況の中古携帯市場 課題は" )
		);
		
		$this->renderJson($data);
	}
	
	public function doRestView($id)
	{
		$data = array(
		array( "post_id" => 1, "time" => "3月3日 12時42分", "title" => "Evernoteが不正アクセス被害、全ユーザーのパスワードをリセット",
			"content" => "米Evernoteは2日、同社のシステムに何者かが不正アクセスしたことを公表した。一部のユーザー情報にアクセスされたとし、同社では対応措置として、全ユーザーのパスワードをリセットした。Evernoteを利用するにはパスワードの再設定が必要になる。(マイナビニュース)" ),
		array( "post_id" => 2, "time" => "3月4日 18時3分", "title" => "Google、浪江町内でストリートビューの撮影を開始",
			"content" => "Googleは4日、福島県双葉郡浪江町内のストリートビューの撮影を開始したことを公表した。(Impress Watch)"  ),
		array( "post_id" => 3, "time" => "3月3日 7時38分", "title" => "ツイッター、年内にも株式上場か　フェイスブックの教訓…次の一手は",
			"content" => "【ワシントン＝柿内公輔】米簡易ブログ大手ツイッターが年内にも株式上場に踏み切るとの観測が高まっている。各国で急増する利用者を強みに、今年始めた携帯端末向け動画投稿サービスがヒットするなどモバイル戦略も強化。企業買収も活発化させ、業容飛躍へ資金調達を広げるとの見方が強まっている。米フェイスブックに続くＩＴの旗手と目される有望企業の動向は注目を集めそうだ。(SankeiBiz)"  ),
		array( "post_id" => 4, "time" => "3月3日 22時14分", "title" => "本名と恥ずかしいDL履歴が一生ネット上に晒される？ファイル共有ソフトの落とし穴",
			"content" => "2月19日から21日までの3日間で、ファイル共有ソフトを利用して、違法なファイルをアップロードしていた27人が逮捕された。一斉取り締まりは、これで4回目となる。(Business Journal)"  ),
		array( "post_id" => 5, "time" => "3月3日 7時29分", "title" => "音楽配信、聴き放題サービス続出のワケ",
			"content" => "突然のブーム襲来である。今、日本の音楽業界では「定額配信」が注目の的だ。(東洋経済オンライン)"  ),
		array( "post_id" => 6, "time" => "3月4日 23時2分", "title" => "ソニーも新ＯＳスマホ　新興国向け、１４年発売目指す",
			"content" => "【長崎潤一郎】ソニーモバイルコミュニケーションズの鈴木国正社長は４日、新しい基本ソフト（ＯＳ）を使った低価格のスマートフォンを開発する方針を明らかにした。南米やアフリカで事業展開する欧州の通信大手テレフォニカとも提携し、新興国市場を開拓する。(朝日新聞デジタル)"  ),
		array( "post_id" => 7, "time" => "3月4日 13時0分", "title" => "「Xperia Z」に復活託すソニー 見通し上回る上々の滑り出し",
			"content" => "【東京】現在日本最大の携帯電話会社NTTドコモのトップは1月の最新機種発表会の席で、ソニーの最新型多機能携帯電話（スマートフォン）「Xperia Z」が同社の「イチ押し」だと語った。母親がどの子を気に入っているかを公言するに等しい発言だ。(ウォール・ストリート・ジャーナル)"  ),
		array( "post_id" => 8, "time" => "3月4日 11時47分", "title" => "中古携帯市場活況　流通拡大「１０００億円規模」　通話料未払い品など課題",
			"content" => "携帯電話の中古市場が盛り上がりをみせている。スマートフォン（高機能携帯電話）の普及に伴って買い替え需要が高まり、スマホだけでなく、従来型の携帯電話が市場に流入し、愛好家の人気を集めている。ただ、通話料金が未払いのまま売り出されるケースも散見されるなど、市場の透明性を高める仕組みづくりが急務となっている。(産経新聞)"  )
		);
		
		$this->renderJson($data[$id - 1]);
	}
	
	public function doRestCreate($data)
	{
		$this->renderJson(array('response' => 'create'));
	}
	
	public function doRestUpdate($id, $data)
	{
		$this->renderJson(array('response' => 'update'));
	}
	
	public function doRestDelete($id)
	{
		$this->renderJson(array('response' => 'delete'));
	}
}