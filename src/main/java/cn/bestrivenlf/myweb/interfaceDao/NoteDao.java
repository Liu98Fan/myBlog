package cn.bestrivenlf.myweb.interfaceDao;

import cn.bestrivenlf.myweb.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select count(*) from note_tb where del_flag = 0 and title like CONCAT('%',#{search},'%');")
    public int getNoteCountBySearch(String search);
    @Select("select count(*) from note_tb where del_flag = 0 and classifyid=#{classifyId};")
    public int getNoteCountByClassifyId(String classifyId);
    @Select("select A.id 'id' , A.title 'title' ,A.author 'author',A.content 'content', B.id 'noteclassify.id',A.date" +
            " 'date',A.newdate 'newdate',A.del_flag 'del_flag',B.classifyName 'noteclassify.classifyname',(select count(*) from note_resource_tb c where c.noteId=A.id and c.del_flag=0) as 'resourceCount'\n" +
            "\n" +
            "from note_tb A LEFT JOIN noteclassify_tb B on  A.classifyid = B.id where A.del_flag=0 and B.del_flag=0 " +
            "ORDER BY date DESC LIMIT #{param1},#{param2};")
    public List<Note> getNotesForPage(int start, int end);
    @Select("select A.id 'id' , A.title 'title' ,A.author 'author',A.content 'content', B.id 'noteclassify.id',A.date" +
            " 'date',A.newdate 'newdate',A.del_flag 'del_flag',B.classifyName 'noteclassify.classifyname',(select count(*) from note_resource_tb c where c.noteId=A.id and c.del_flag=0) as 'resourceCount'\n" +
            "\n" +
            "from note_tb A LEFT JOIN noteclassify_tb B on  A.classifyid = B.id where A.del_flag=0 and A.classifyid=#{classifyId} and B.del_flag=0 " +
            "ORDER BY date DESC LIMIT #{start},#{end};")
    public List<Note> getNotesForPageByClassifyId(@Param("start") int start, @Param("end") int end, @Param("classifyId") String classifyId);
    @Select("select A.id 'id' , A.title 'title' ,A.author 'author',A.content 'content', B.id 'noteclassify.id',A.date" +
            " 'date',A.newdate 'newdate',A.del_flag 'del_flag',B.classifyName 'noteclassify.classifyname',(select count(*) from note_resource_tb c where c.noteId=A.id and c.del_flag=0) as 'resourceCount'\n" +
            "\n" +
            "from note_tb A LEFT JOIN noteclassify_tb B on  A.classifyid = B.id where A.del_flag=0 and A.title like CONCAT('%',#{search},'%') and B.del_flag=0 " +
            "ORDER BY date DESC LIMIT #{start},#{end};")
    public List<Note> getNotesForPageBySearch(@Param("start") int start,@Param("end") int end, @Param("search") String search);
    @Select("select A.id 'id' , A.title 'title' ,A.author 'author',A.content 'content', B.id 'noteclassify.id',A.date" +
            " 'date',A.newdate 'newdate',A.del_flag 'del_flag',B.classifyName 'noteclassify.classifyname',(select count(*) from note_resource_tb c where c.noteId=A.id and c.del_flag=0) as 'resourceCount'\n" +
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
    //----------------------评论功能---------------------------------//

    /**
     * 获取一篇笔记下的所有评论
     * @param noteId
     * @return
     */
    @Select("select a.id as 'comment.id' ,\n" +
            "a.noteId as 'comment.noteId',\n" +
            "a.userId as 'comment.userId',\n" +
            "a.content as 'comment.content',\n" +
            "a.textContent as 'comment.textContent',\n" +
            "a.agree as 'comment.agree',\n" +
            "a.date as 'comment.date',\n" +
            "a.del_flag as 'comment.del_flag',\n" +
            "b.username as 'user.username' ,\n" +
            "(select count(*) from reply_tb c where c.commentId = a.id ) as 'replyCount' from comment_tb a LEFT JOIN user_account_tb b on a.userId = b.id where a.del_flag = 0 and a.noteId = #{noteId} ORDER BY a.date ASC")
    public List<CommentPOJO> getCommentByNoteId(String noteId);

    /**
     * 获取一篇笔记下的所有评论的分页数据
     * @param noteId
     * @return
     */

    public List<Comment> getCommentByNoteIdForPage(String noteId,int start,int end);

    /**
     * 获取一篇评论下的所有回复
     * @param commentId
     * @return
     */
    public List<Reply> getReplyByCommentId(String commentId);

    /**
     * 获取一篇评论下的所有回复的分页数据
     * @param commentId
     * @return
     */
    public List<Reply> getReplyByCommentIdForPage(String commentId,int start,int end);

    /**
     * 插入一条评论
     * @param comment
     * @return
     */
    @Select("call saveComment(#{id},#{noteId},#{userId},#{content},#{textContent},#{agree},#{date},#{del_flag})")
    public String saveComment(Comment comment);

    /**
     * 插入一条回复
     * @param reply
     * @return
     */
    @Select("call saveReply(#{id},#{commentId},#{userId},#{sequence},#{content},#{replyTo},#{date},#{del_flag})")
    public String saveReply(Reply reply);

    /**
     * 删除一条评论，应当删除其下所有回复
     * @param comment
     * @return
     */
    public String deleteComment(Comment comment);

    /**
     * 删除一条回复
     * @param reply
     * @return
     */

    @Select("select \n" +
            "a.id as 'reply.id',\n" +
            "a.commentId as 'reply.commentId',\n" +
            "a.userId as 'reply.userId',\n" +
            "a.sequence as 'reply.sequence',\n" +
            "a.content as 'reply.content',\n" +
            "a.replyTo as 'reply.replyTo',\n" +
            "a.date as 'reply.date',\n" +
            "a.del_flag as 'reply.del_flag',\n" +
            "b.id as 'user.id',\n" +
            "b.username as 'user.username'\n" +
            "from reply_tb a left JOIN user_account_tb b on a.userId=b.id where a.del_flag=0 and a.commentId=#{commentId} ORDER BY a.date ASC")
    public List<ReplyPOJO> getReplyListByCommentId(String commentId);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Select("call deleteComment(#{commentId})")
    public String deleteComment(String commentId);

    //----------------------------------------------资源模块---------------------------------//
    @Update("update note_resource_tb set del_flag = 0 where noteId = #{noteId}")
    public void clearResourceByNoteId(String noteId);
    @Select("call saveResourceByNoteId(#{file.id},#{file.resourceName},#{file.resourceType},#{file.vitrualPath},#{file.absolutePath},#{file.md5},#{file.description},#{file.date},#{file.del_flag},#{noteId})")
    public String saveResourceByNoteId(FileEntity file,String noteId);
    @Select("select * from resource_tb a LEFT JOIN note_resource_tb b on a.id = b.resourceId where b.noteId=#{id} and b.del_flag=0")
    public List<FileEntity> getResourceByNoteId(String id);
    @Select("call removeResourceById(#{id},#{noteId})")
    public String removeResourceById(String id,String noteId);
}
