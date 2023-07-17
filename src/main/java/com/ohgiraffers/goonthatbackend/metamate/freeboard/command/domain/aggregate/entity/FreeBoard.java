package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity;
import com.ohgiraffers.goonthatbackend.metamate.comment.command.domain.aggregate.entity.Comment;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo.*;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.time.LocalDate;


@Entity
@Table(name = "FREE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FREE_BOARD_NO")
    private Long boardNo;


    private BoardCategory boardCategory;

    private BoardTitle boardTitle;

    private BoardContent boardContent;

    private BoardWriter boardWriter;

    private BoardHits boardHits;

    private BoardDeleteYn boardDeleteYn;

    @Embedded
    private BoardDate boardDate;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

}

    public FreeBoard(BoardTitle boardTitle, BoardContent boardContent, BoardDeleteYn boardDeleteYn, BoardDate boardDate, BoardCategory boardCategory){
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardDeleteYn = boardDeleteYn;
        this.boardDate = new BoardDate(LocalDate.now(), LocalDate.now());
        this.boardCategory = boardCategory;
    }

    public FreeBoard(BoardCategory boardCategory, BoardTitle boardTitle, BoardContent boardContent) {
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public FreeBoard(Long boardNo, BoardTitle boardTitle, BoardDate boardDate) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardDate = new BoardDate(LocalDate.now(), LocalDate.now());
    }

    public void setBoardDeleteYn(BoardDeleteYn boardDeleteYn) {
        this.boardDeleteYn = boardDeleteYn;
    }

    @Override
    public String toString() {
        return "FreeBoard{" +
                "boardNo=" + boardNo +
                ", boardCategory=" + boardCategory +
                ", boardTitle=" + boardTitle +
                ", boardContent=" + boardContent +
                ", boardWriter=" + boardWriter +
                ", boardHits=" + boardHits +
                ", boardDeleteYn=" + boardDeleteYn +
                ", boardDate=" + boardDate +
                '}';
    }
}

