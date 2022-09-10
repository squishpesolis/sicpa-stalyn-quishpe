package com.bp.example.base.prueba;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bp.example.base.exceptions.CustomException;
import com.bp.example.base.service.impl.GenericServiceImpl;
import com.bp.example.departments.entities.DepartmentOld;
import com.bp.example.departments.service.DepartmentService;


@Service("postServiceImpl")
@Transactional
public class PostServiceImpl extends GenericServiceImpl<Post> implements PostService{


	@Autowired
	private TagService tagService;
	
	@Override
	public Post guardar(Post entidad) throws CustomException {
		
		Post post = new Post();
		post.setTitle(entidad.getTitle());
		
		List<PostTag> listaTags = entidad.getTags();
		
		for (PostTag postTag : listaTags) {
			
			Tag tageSave = tagService.buscarPorId(postTag.getTag().getIdTag());
			
			post.addTag(tageSave);
		}
		
		return super.guardar(post);
	}
	
}
