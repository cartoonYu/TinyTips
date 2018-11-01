package com.cartoon.tinytips.Note.Details;

import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.cartoon.tinytips.BaseActivity;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.bean.Note;
import com.cartoon.tinytips.util.IntentActivity;
import com.cartoon.tinytips.util.Note.DivideNote;
import com.cartoon.tinytips.util.UI.RevampStatusBar;
import com.cartoon.tinytips.util.UI.RevampToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoteDetail extends BaseActivity<NoteDetailPresenter> implements INoteDetail.View{

    private Note note;

    private int Note;
    @BindView(R.id.statusBar)
    View statusBar;

    @BindView(R.id.toolbarBack)
    TextView back;

    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @BindView(R.id.note_detail_title)
    TextView title;

    @BindView(R.id.note_detail_contents)
    TextView details;

    @BindView(R.id.note_detail_date)
    TextView date;


    private String CT1 ="一、优点\n" +
            "保留了经典Java应用的架构，但开发、运行成本更低\n" +
            "\n" +
            "二、Java EE应用的分层模型\n" +
            "1、Domain Object（领域对象）层（也称为 持久层）：由一系列的POJO组成，这些对象往往包含着各自所需实现的业务逻辑方法，可以理解为操作数据的人\n" +
            "\n" +
            "2、DAO（Data Access Object，数据访问层）：此层由一系列DAO组成，DAO实现了对数据库的增删查改（CRUD）等原子操作，可以理解为操作数据的工具\n" +
            "\n" +
            "3、业务逻辑层：实现核心功能的的逻辑方法，可能仅仅用于暴露Domain Object对象所实现的业务逻辑层\n" +
            "\n" +
            "4、控制器层：拦截并处理用户请求，根据处理结果转发到不同的表现层组件\n" +
            "\n" +
            "5、前端层";

    private String CT2 ="HttpRequestException encountered解决方法\n\n"+
            "问题原因是Github 禁用了TLS v1.0 and v1.1，必须更新Windows的git凭证管理器。\n"+
            "通过此网址https://github.com/Microsoft/Git-Credential-Manager-for-Windows/releases/\n";

    private String CT3 ="举例：\n" +
            "1、商品推荐：要解决的问题：（1）大量的数据如何存储？（2）大量数据如何计算\n" +
            "\n" +
            "2、天气预报：要解决的问题：（1）大量的天气数据如何存储？（2）大量的天气数据如何计算\n" +
            "\n" +
            "大数据开发的本质\n" +
            "1、数据的存储:分布式文件系统（分布式存储）\n" +
            "\n" +
            "2、数据的计算：分布式计算";

    private String CT4 ="学习路线：\n" +
            "\t \n" +
            "java基础和Linux基础\n" +
            "\t \n" +
            "Hadoop的学习\n" +
            "\n" +
            "\n" +
            "第一阶段：HDFS、MapReduce、HBase（NoSQL数据库）\n" +
            "\n" +
            "第二阶段：数据分析引擎------Hive、Pig\n" +
            "                             数据采集引擎------Sqoop、Flume\n" +
            "\n" +
            "第三阶段：HUE--Web管理工具\n" +
            "                              ZooKeeper:实现Hadoop的HA\n" +
            "                              Oozie：工作引擎流\n" +
            "\t \n" +
            "Spark的学习\n\n\n" +
            "第一阶段：Scala编程语言\n" +
            "第二阶段：Spark Core ---------基于内存，数据的计算\n" +
            "第三阶段：Spark SQL -------- 类似Oracle中的SQL语句\n" +
            "第四阶段：Spark Streaming -----进行实时计算（流式计算）\n" +
            "\t* \n" +
            "Apache Storm：类似Spark Streaming -----进行实时计算（流式计算）\n\n\n" +
            "NoSQL : Redis基于内存的数据库";

    @Override
    protected NoteDetailPresenter initPresent(){
        return new NoteDetailPresenter(this);
    }

    @Override
    protected int getLayout(){
        return R.layout.note_detail;
    }

    @Override
    protected void initView(){
        revampStatusBar();
        initToolbar();
    }

    @Override
    protected void onPrepare(){
       // initData();
        Note = IntentActivity.getIntentData(this,"NoteList_note",Note);
        switch(Note){
            case 0:{
                title.setText("JavaEE应用");
                details.setText(CT1);
                date.setText("2018年5月2号");
                break;
            }

            case 1:{
                title.setText("HttpRequestException encountered解决方法");
                details.setText(CT2);
                date.setText("2018年5月9号");
                break;
            }

            case 2:{
                title.setText("什么是大数据");
                details.setText(CT3);
                date.setText("2018年5月4号");
                break;
            }

            case 3:{
                title.setText("学习大数据的路线");
                details.setText(CT4);
                date.setText("2018年5月5号");
                break;
            }
        }
    }

   private void initData() {
        if((Note)getIntent().getSerializableExtra("note")!=null){
            note=(Note)getIntent().getSerializableExtra("note");
        }
        setTitle();
        setDetails();
        setDate();
    }


    @OnClick(R.id.toolbarBack)
    protected void onClickBack(){
        IntentActivity.finishActivity(this);
    }

    @Override
    public void onBackPressed(){
        IntentActivity.finishActivity(this);
    }

    /**
     * 沉浸式任务栏
     */
    private void revampStatusBar(){
        RevampStatusBar.revampStatusBar(statusBar,R.color.white);
    }

    /**
     * 修改toolbar显示
     */
    private void initToolbar(){
        RevampToolbar.setBack(back);
        RevampToolbar.setText(toolbarText,new String("详情"));
    }

    @Override
    public void setTitle(){
        this.title.setText(note.getTitle());
    }

    @Override
    public void setDetails(){
      List<SpannableString> stringList=DivideNote.getDivideNote().transNoteToString(note);
        for(SpannableString string:stringList){
            this.details.append(string);
        }
    }

    @Override
    public void setDate(){
        this.date.setText(note.getDate());
    }

}
