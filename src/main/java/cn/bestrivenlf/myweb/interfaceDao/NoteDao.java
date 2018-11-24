package cn.bestrivenlf.myweb.interfaceDao;

import cn.bestrivenlf.myweb.entity.Note;
import cn.bestrivenlf.myweb.entity.NoteClassify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: liufan
 * @Date: 2018/9/6 21:08
 * @Description:
 */
@Mapper
public interface NoteDao {
    @Select("call saveNote(#{id},#{title},#{author},#{content},#{date},#{newdate},#{del_flag},#{noteclassify.id}," +
            "#{noteclassify.classifyname});")
    public Note saveNote(Note note);
    @Select("select count(*) from note_tb where del_flag = 0;")
    public int getNoteCount();
    @Select("select A.id 'id' , A.title 'title' ,A.author 'author',A.content 'content', B.id 'noteclassify.id',A.date" +
            " 'date',A.newdate 'newdate',A.del_flag 'del_flag',B.classifyName 'noteclassify.classifyname'\n" +
            "\n" +
            "from note_tb A LEFT JOIN noteclassify_tb B on  A.classifyid = B.id where A.del_flag=0 and B.del_flag=0 " +
            "ORDER BY date DESC LIMIT #{param1},#{param2};")
    public List<Note> getNotesForPage(int start, int end);
    @Select("select A.id 'id' , A.title 'title' ,A.author 'author',A.content 'content', B.id 'noteclassify.id',A.date" +
            " 'date',A.newdate 'newdate',A.del_flag 'del_flag',B.classifyName 'noteclassify.classifyname'\n" +
            "\n" +
            "from note_tb A LEFT JOIN noteclassify_tb B on  A.classifyid = B.id where A.del_flag=0 and B.del_flag=0 " +
            "and A.id = #{id}")
    public Note getNote(String id);
    @Select("select * from noteclassify_tb where del_flag = 0 ")
    public List<NoteClassify> getNoteClassify();

    @Select("select count(*) from note_tb where del_flag = 0 and classifyid = #{cid}")
    public Integer getClassifyNumber(String cid);

    @Select("select count(*) 'number',classifyid 'id',B.classifyName,B.date,B.del_flag from note_tb  A\n" +
            "left JOIN noteclassify_tb B on A.classifyid = B.id\n" +
            "where A.del_flag=0 GROUP BY classifyid ")
    public List<NoteClassify> getClassifyNumberList();
    @Select("update note_tb set del_flag = 1 where id = #{id} ")
    public void deleteNote(String id);
    @Select("select * from note_tb where del_flag = 0;")
    public List<Note> getAllNote();
}
