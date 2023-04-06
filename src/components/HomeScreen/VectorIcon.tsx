import { memo, SVGProps } from 'react';

const VectorIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg preserveAspectRatio='none' viewBox='0 0 16 14' fill='none' xmlns='http://www.w3.org/2000/svg' {...props}>
    <path
      d='M6.08897 10.0385L10.6557 6.92308L6.08897 3.80769V10.0385ZM7.61121 0C3.40982 0 0 3.10154 0 6.92308C0 10.7446 3.40982 13.8462 7.61121 13.8462C11.8126 13.8462 15.2224 10.7446 15.2224 6.92308C15.2224 3.10154 11.8126 0 7.61121 0ZM7.61121 12.4615C4.25467 12.4615 1.52224 9.97615 1.52224 6.92308C1.52224 3.87 4.25467 1.38462 7.61121 1.38462C10.9678 1.38462 13.7002 3.87 13.7002 6.92308C13.7002 9.97615 10.9678 12.4615 7.61121 12.4615Z'
      fill='black'
    />
  </svg>
);

const Memo = memo(VectorIcon);
export { Memo as VectorIcon };
