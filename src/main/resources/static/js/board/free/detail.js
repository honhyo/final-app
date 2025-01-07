import * as likeApi from './modules/likeApi.js'


{
  // DOM 요소
  const $likeBtn = document.querySelector('.like-btn');
  const $deleteBtn = document.querySelector('.delete-btn');
  const $commentForm = document.querySelector('.comment-form');
  const $commentInput = document.querySelector('.comment-input');
  const $commentList = document.querySelector('.comment-list');

  { //진입과 동시에 좋아요 체크
    likeApi.checkLike(freeBoardId, function (data) {
      console.log(data);
  //TODO:전달받은 데이터를 활용하여 좋아요 버튼의 상태와 좋아요 수 반영하기!
    });

  }

  // 좋아요 버튼 클릭
  $likeBtn.addEventListener('click', function () {
    this.classList.toggle('active');
    const $likeCount = this.querySelector('.like-count');
    const currentCount = parseInt($likeCount.textContent);

    // TODO: 서버에 좋아요 토글 처리, 반영된 결과 다시 조회해서 화면 처리
    if (this.classList.contains('active')) {
      $likeCount.textContent = currentCount + 1;
    } else {
      $likeCount.textContent = currentCount - 1;
    }
  });

  // 게시글 삭제
  //JS,타임리프는 .?을 제공한다
  //.?:옵셔녈 체이닝 연산자
  //특정 객체에 접근할떄 null,undefined 인지 확인해야함
  //자바의 경우 옵서녀ㅑㄹ 객체를 사용한다
  //js의 경우 옵셔널 체이닝 연산자를 활용하여, 해당 개ㅑㄱ체가 null이 아닌 경우에만 접근하도록 자동처리
  $deleteBtn?.addEventListener('click', function () {
    if (confirm('정말 삭제하시겠습니까?')) {
      // TODO: 서버에 삭제 요청
      const boardId=this.dataset.boardId;
      location.href = `/board/free/delete?freeBoardId=${boardId}`;
    }
  });

  // 댓글 작성
  $commentForm.addEventListener('submit', function (e) {
    e.preventDefault();

    const content = $commentInput.value.trim();
    if (!content) {
      alert('댓글 내용을 입력해주세요.');
      $commentInput.focus();
      return;
    }

    // TODO: 서버에 댓글 작성 요청
    // 성공 시 댓글 목록 새로고침
  });

  // 댓글 수정
  document.addEventListener('click', function (e) {
    if (e.target.classList.contains('edit-comment-btn')) {
      const $commentArticle = e.target.closest('.comment-article');
      const $commentText = $commentArticle.querySelector('.comment-text');
      const currentText = $commentText.textContent;

      // 이미 수정 중인 상태라면 리턴
      if ($commentArticle.classList.contains('editing')) return;

      // 수정 폼 생성
      const $editForm = document.createElement('form');
      $editForm.className = 'edit-form';
      $editForm.innerHTML = `
          <textarea class="edit-input">${currentText}</textarea>
          <div class="edit-actions">
              <button type="submit" class="save-btn">저장</button>
              <button type="button" class="cancel-btn">취소</button>
          </div>
      `;

      // 수정 상태로 변경
      $commentArticle.classList.add('editing');
      $commentText.style.display = 'none';
      $commentText.after($editForm);

      const $editInput = $editForm.querySelector('.edit-input');
      $editInput.focus();

      // 취소 버튼 클릭
      $editForm.querySelector('.cancel-btn').addEventListener('click', function () {
        $commentArticle.classList.remove('editing');
        $commentText.style.display = '';
        $editForm.remove();
      });

      // 수정 폼 제출
      $editForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const newText = $editInput.value.trim();
        if (!newText) {
          alert('댓글 내용을 입력해주세요.');
          $editInput.focus();
          return;
        }

        // TODO: 서버에 수정 요청
        $commentText.textContent = newText;
        $commentArticle.classList.remove('editing');
        $commentText.style.display = '';
        $editForm.remove();
      });
    }
  });

  // 댓글 삭제
  document.addEventListener('click', function (e) {
    if (e.target.classList.contains('delete-comment-btn')) {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        const $commentItem = e.target.closest('.comment-item');
        // TODO: 서버에 삭제 요청
        $commentItem.remove();
      }
    }
  });
}
