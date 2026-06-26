import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidateJobList } from './candidate-job-list';

describe('CandidateJobList', () => {
  let component: CandidateJobList;
  let fixture: ComponentFixture<CandidateJobList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CandidateJobList],
    }).compileComponents();

    fixture = TestBed.createComponent(CandidateJobList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
